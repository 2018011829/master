package com.group.tiantian.moments.servlet.likeGive;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.LikeGiveInfo;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.LikeGiveService;

/**
 * Servlet implementation class likegivePersonAndMomentsId
 */
@WebServlet("/LikegivePersonAndMomentsId")
public class LikegivePersonAndMomentsId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����Gson��������
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikegivePersonAndMomentsId() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		initGson();//��ʼ��gson����
		// ��ȡ��������
		String likegivePerson = request.getParameter("likegivePerson");// �õ������˵��ֻ���
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// �õ�������˵˵��id
		System.out.println("�ֻ���"+likegivePerson + ":" + momentsId);

		LikeGiveService likeGiveService = LikeGiveService.getInstance();// ��������service����
		PersonalInfo personalInfo = likeGiveService.getPersonalInfo("19831127375");// ���ݵ����˵��ֻ��ŵõ������˵ĸ�����Ϣ
		String likeGiveName = personalInfo.getName();// �õ������˵��ǳ�
		likeGiveService.insertLikeGiveInfo(momentsId, likeGiveName, likegivePerson);// ��������˵˵id���������ǳƺ͵������ֻ��Ŵ������ݿ�
		List<String> likeGiveNames = likeGiveService.likeGiveNames(momentsId);// ��ȡ����˵˵�������ǳ��б�
		String json0 = gson.toJson(likeGiveNames);//���������ǳ��б����json����
		LikeGiveInfo likeGiveInfo = new LikeGiveInfo(json0, likeGiveNames.size());// ���������б�͵����˵����������һ��������Ϣ����
		//���л�
        String json = gson.toJson(likeGiveInfo);//����Ϣ������json��
		// ������Ӧ
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
