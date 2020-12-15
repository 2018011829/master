package com.group.tiantian.moments.servlet.moments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.Attention;
import com.group.tiantian.entity.moments.Comment;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.AttentionService;
import com.group.tiantian.moments.service.CommentsService;
import com.group.tiantian.moments.service.LikeGiveService;
import com.group.tiantian.moments.service.MomentsService;
import com.group.tiantian.util.ConfigUtil;

/**
 * Servlet implementation class MineMomentsInfoSerclet
 */
@WebServlet("/MineMomentsInfoSerclet")
public class MineMomentsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����Gson��������
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MineMomentsInfoServlet() {
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
		// request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ��������
		String personPhone = request.getParameter("PersonPhone");// �����˵��ֻ���
		// Gson����ʵ����
		initGson();
		List<Moments> moments = new ArrayList<>();// ˵˵�б�
		moments.clear();
		List<String> picturesUrl = new ArrayList<>();// ͼƬ�����ַ�б�
		picturesUrl.clear();
		List<MomentsPicture> pictures = new ArrayList<>();// ͼƬ�����б�
		pictures.clear();
		List<String> likeGiveNames = new ArrayList<>();// �����ǳ��б�
		likeGiveNames.clear();
		List<List> listPicture = new ArrayList<>();// ͼƬ�����б���б�
		listPicture.clear();
		List<Comment> commentInfo = new ArrayList<>();// ������Ϣ�б�
		commentInfo.clear();
		List<Attention> attentionList = new ArrayList<>();// ��ע�б�
		attentionList.clear();
		String content = null;// ˵˵�İ�
		int likegiveboolen = 0;
		PersonalInfo personalInfo = new PersonalInfo();

		// ��ȡվ���Ŀ¼�µ�ͼƬ�����·��
		String root = getServletContext().getContextPath();
		// ����ͼƬ����·��
		String urlPath = ConfigUtil.SERVICE_ADDRESS + "imgs/";

		MomentsService addMomentsService = MomentsService.getInstance();
		LikeGiveService likeGiveService = LikeGiveService.getInstance();
		CommentsService commentsService = CommentsService.getInstance();
		AttentionService attentionService = AttentionService.getInstance();

		moments = addMomentsService.getMomentsByPhone(personPhone);// ��ȡ����˵˵��Ϣ
		for (int i=0; i<moments.size();i++) {
			pictures = addMomentsService.getMomentsPicture(moments.get(i).getId());// ����˵˵id��ȡ����ͼƬ����
			content = addMomentsService.getMomentsContent(moments.get(i).getId());// ����˵˵id��ȡ˵˵�İ�
			personalInfo = addMomentsService.getPersonalInfo(moments.get(i).getPhoneNumber());// ���ݵ绰�����ȡͷ����ǳ�
			likeGiveNames = likeGiveService.likeGiveNames(moments.get(i).getId());// ����˵˵id��ȡ�����ǳ��б�
			likegiveboolen = likeGiveService.selectLikeGiveboolean(moments.get(i).getId(), personPhone);// ����˵˵id���û��ֻ��Ż�ȡ�������
			commentInfo = commentsService.commentsInfo(moments.get(i).getId());// ����˵˵id��ȡ�������
			attentionList = attentionService.getAttentionList(personPhone);// ���ݵ�ǰ�û��ֻ��Ż�ȡ��ע�б�
			if (likeGiveNames.isEmpty()) {
				likeGiveNames.add("û���˵�����");
			}
			for (int j = 0; j < pictures.size(); j++) {
				String img = urlPath + pictures.get(j).getMomentsPictureUrl();
				picturesUrl.add(img);
			}
			String json1 = gson.toJson(picturesUrl);
			moments.get(i).setPictureUrl(json1);// ����ͼƬ
			moments.get(i).setContent(content);// ����˵˵�İ�
			moments.get(i).setName(personalInfo.getName());// ���ø����ǳ�
			moments.get(i).setLikegiveboolen(likegiveboolen);// ���õ�ǰ�û��Ƿ�Դ�˵˵����
			String json2 = gson.toJson(likeGiveNames);
			moments.get(i).setLikeGiveName(json2);// ���õ��޴�˵˵���˵��ǳ�
			String json3 = gson.toJson(commentInfo);
			moments.get(i).setComments(json3);// ���ô�˵˵��������Ϣ
			String json4 = gson.toJson(attentionList);
			moments.get(i).setAttentionList(json4);
			// ����ͷ��ͼƬ����·��
			String urlPath1 = ConfigUtil.SERVICE_ADDRESS + "avatar/";
			moments.get(i).setHeadPortraitUrl(urlPath1 + personalInfo.getPhotoUrl());// ���÷�˵˵�û���ͷ��
			picturesUrl.clear();
		}
		Collections.reverse(moments);
		// ���л�
		String json = gson.toJson(moments);
		// ��ȡ���������������ͼƬ��������Դ·�����ظ��ͻ���
		response.getWriter().write(json);
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
