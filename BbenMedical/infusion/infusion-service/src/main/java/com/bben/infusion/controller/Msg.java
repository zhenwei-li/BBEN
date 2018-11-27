package com.bben.infusion.controller;

import com.bben.infusion.transmission.VideoVoiceSocket;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/WebRTC")
public class Msg extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @GetMapping("/msg")
    @ResponseBody
	public JSONObject Msg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();

		HttpSession session = request.getSession();
		
		String oid = request.getParameter("oid");
		String uid = session.getId();
        jsonObject.put("initiator", "false");// 是否已经有人在等待
		
		if(!VideoVoiceSocket.canCreate()) {
            jsonObject.put("msg","不能创建通话房间，超过最大创建数量！");
			return jsonObject;
		}
		
		if(!VideoVoiceSocket.canJoin(oid)) {
            jsonObject.put("msg","对不起对方正在通话中，你不能加入！");
            return jsonObject;
		}
		
		if(VideoVoiceSocket.addUser(uid, oid)) {
            jsonObject.put("uid", uid);
		} else {
            jsonObject.put("initiator", "true");
            jsonObject.put("uid", uid);
            jsonObject.put("oid", oid);
		}
        return jsonObject;
	}

}
