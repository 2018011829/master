package com.group.tiantian.moments.servlet.addMoments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.AddMomentsService;
import com.group.tiantian.util.ConfigUtil;

/**
 * Servlet implementation class DownLoadImgNameServlet
 */
@WebServlet("/DownPictureServlet")
public class DownPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����Gson��������
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownPictureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ��뷽ʽ
		//request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// Gson����ʵ����
		initGson();
		List<Moments> moments = new ArrayList<>();// ˵˵�б�
		moments.clear();
		List<String> picturesUrl = new ArrayList<>();// ͼƬ�����ַ�б�
		picturesUrl.clear();
		List<MomentsPicture> pictures = new ArrayList<>();// ͼƬ�����б�
		pictures.clear();
		List<List> listPicture = new ArrayList<>();// ͼƬ�����б���б�
		String content = null;//˵˵�İ�
		PersonalInfo personalInfo = new PersonalInfo();
		
		// ��ȡվ���Ŀ¼�µ�ͼƬ�����·��
		String root = getServletContext().getContextPath();
		// ����ͼƬ����·��
		String urlPath = ConfigUtil.SERVICE_ADDRESS + "imgs/";

		// ��ȡ��ǰ����˵˵id������˵˵id��ȡͼƬ���ƺ�˵˵�İ�
		AddMomentsService addMomentsService = AddMomentsService.getInstance();
		moments = addMomentsService.getMoments();// ��ȡ����˵˵��Ϣ
		
		for (int i = 0; i < moments.size(); i++) {
			pictures = addMomentsService.getMomentsPicture(moments.get(i).getId());//����˵˵id��ȡ����ͼƬ����
			content = addMomentsService.getMomentsContent(moments.get(i).getId());//����˵˵id��ȡ˵˵�İ�
			personalInfo = addMomentsService.getPersonalInfo(moments.get(i).getPhoneNumber());//���ݵ绰�����ȡͷ����ǳ�
			for (int j = 0; j < pictures.size(); j++) {
				String img = urlPath + pictures.get(j).getMomentsPictureUrl();
				picturesUrl.add(img);
			}

			String json1 = gson.toJson(picturesUrl);
			moments.get(i).setPictureUrl(json1);//����ͼƬ
			moments.get(i).setContent(content);//����˵˵�İ�
			moments.get(i).setName(personalInfo.getName());//���ø����ǳ�
			// ����ͷ��ͼƬ����·��
			String urlPath1 = ConfigUtil.SERVICE_ADDRESS + "avatar/";
			moments.get(i).setHeadPortraitUrl(urlPath1+personalInfo.getPhotoUrl());
			picturesUrl.clear();
		}
		
		 //���л�
        String json = gson.toJson(moments);

		System.out.println(json);
		// ��ȡ���������������ͼƬ��������Դ·�����ظ��ͻ���
		response.getWriter().write(json);
		// picturesUrl.toString().substring(1, picturesUrl.toString().length() - 1)
	}

	/**
	 * ��ʼ��Gson����
	 */
	private void initGson() {
		gson = new GsonBuilder()// ����GsonBuilder����
				.setPrettyPrinting()// ��ʽ�����
				.serializeNulls()// �������Nullֵ����
				.setDateFormat("YY:MM:dd")// ���ڸ�ʽ��
				.create();// ����Gson����
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
