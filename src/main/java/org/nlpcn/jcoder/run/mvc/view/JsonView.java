package org.nlpcn.jcoder.run.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nlpcn.jcoder.util.ApiException;
import org.nutz.mvc.View;

import com.alibaba.fastjson.JSONObject;

/**
 * 直接返回正文
 * 
 * @author ansj
 * 
 */
public class JsonView implements View {

	private int httpStatus = ApiException.OK;
	private Object result;

	public JsonView(int httpStatus, Object result) {
		this.result = result;
		this.httpStatus = httpStatus;
	}

	public JsonView(Object result) {
		this.result = result;
	}
	
	public JsonView() {
	}

	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Throwable {
		resp.setStatus(httpStatus);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("application/json");
		if (obj == null) {
			obj = result;
		}
		resp.getWriter().write(toString(obj));
		resp.flushBuffer();
	}

	public String toString(Object result) {
		if (result instanceof String) {
			return (String) result;
		}
		return JSONObject.toJSONString(result);
	}
}