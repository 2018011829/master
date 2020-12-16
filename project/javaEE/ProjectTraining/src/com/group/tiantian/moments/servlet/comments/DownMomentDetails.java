package com.group.tiantian.moments.servlet.comments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.Comment;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.entity.moments.ReplyContent;
import com.group.tiantian.moments.service.MomentsService;
import com.group.tiantian.moments.service.CommentsService;
import com.group.tiantian.moments.service.LikeGiveService;
import com.group.tiantian.moments.service.ReplyContentService;
import com.group.tiantian.util.ConfigUtil;

/**
 * Servlet implementation class DownMomentDetails
 */
@WebServlet("/DownMomentDetails")
public class DownMomentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����Gson��������
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownMomentDetails() {
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
		// ��ȡ��������
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// ��ȡ��Ҫ��ȡ��Ϣ��˵˵id
		String likegivePersonPhone = request.getParameter("likegivePersonPhone");//�����˵��ֻ���
		
		initGson();// Gson�����ʼ��
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
		List<ReplyContent> replyContentInfo = new ArrayList<>();//�ظ���Ϣ�б�
		replyContentInfo.clear();
		String content = null;// ˵˵�İ�
		int likegiveboolen = 0;
		PersonalInfo personalInfo = new PersonalInfo();

		// ��ȡվ���Ŀ¼�µ�ͼƬ�����·��
		String root = getServletContext().getContextPath();
		// ����ͼƬ����·��
		String urlPath = ConfigUtil.SERVICE_ADDRESS + "imgs/";

		// ��ȡ��ǰ����˵˵id������˵˵id��ȡͼƬ���ƺ�˵˵�İ�
		MomentsService addMomentsService = MomentsService.getInstance();
		LikeGiveService likeGiveService = LikeGiveService.getInstance();
		CommentsService commentsService = CommentsService.getInstance();
		ReplyContentService replyContentService = ReplyContentService.getInstance();
		Moments moment = addMomentsService.getMomentsInfo(momentsId);

		pictures = addMomentsService.getMomentsPicture(momentsId);// ����˵˵id��ȡ����ͼƬ����
		content = addMomentsService.getMomentsContent(momentsId);// ����˵˵id��ȡ˵˵�İ�
		personalInfo = addMomentsService.getPersonalInfo(moment.getPhoneNumber());// ���ݵ绰�����ȡͷ����ǳ�
		likeGiveNames = likeGiveService.likeGiveNames(momentsId);// ����˵˵id��ȡ�����ǳ��б�
		likegiveboolen = likeGiveService.selectLikeGiveboolean(momentsId, likegivePersonPhone);// ����˵˵id���û��ֻ��Ż�ȡ�������
		commentInfo = commentsService.commentsInfo(momentsId);// ����˵˵id��ȡ�������
		replyContentInfo = replyContentService.replyContentInfo(momentsId);
		//����˵˵id��ȡ�ظ����
		if (likeGiveNames.isEmpty()) {
			likeGiveNames.add("û���˵�����");
		}
		for (int j = 0; j < pictures.size(); j++) {
			String img = urlPath + pictures.get(j).getMomentsPictureUrl();
			picturesUrl.add(img);
		}
		String json1 = gson.toJson(picturesUrl);
		moment.setPictureUrl(json1);// ����ͼƬ
		moment.setContent(content);// ����˵˵�İ�
		moment.setName(personalInfo.getName());// ���ø����ǳ�
		moment.setLikegiveboolen(likegiveboolen);// ���õ�ǰ�û��Ƿ�Դ�˵˵����
		String json2 = gson.toJson(likeGiveNames);
		moment.setLikeGiveName(json2);// ���õ��޴�˵˵���˵��ǳ�
		String json3 = gson.toJson(commentInfo);
		moment.setComments(json3);// ���ô�˵˵��������Ϣ
		String json4 = gson.toJson(replyContentInfo);
		moment.setReplyContent(json4);
		// ����ͷ��ͼƬ����·��
		String urlPath1 = ConfigUtil.SERVICE_ADDRESS + "avatar/";
		moment.setHeadPortraitUrl(urlPath1 + personalInfo.getPhotoUrl());// ���÷�˵˵�û���ͷ��
		picturesUrl.clear();

		// ���л�
		String json = gson.toJson(moment);

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
