package core.controller;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.extjs.ExtJSBaseParameter;
import core.extjs.ListView;
import core.service.Service;
import core.support.BaseParameter;
import core.support.QueryResult;
import core.web.CustomDateEditor;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
public abstract class ExtJSBaseController<E extends ExtJSBaseParameter> {

	public static final String CMD_EDIT = "edit";

	public static final String CMD_NEW = "new";

	public static final String MODEL = "model";

	protected String idField;

	protected String statusField;

	protected static final String separator = "/";

	protected Service<E> service;

	protected static ObjectMapper mapper = new ObjectMapper();

	protected static JsonFactory factory = mapper.getJsonFactory();

	public ExtJSBaseController() {
	}

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void doList(@ModelAttribute E entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		beforeList(entity);
		BaseParameter parameter = (BaseParameter) entity;
		QueryResult<E> qr = service.doPaginationQuery(parameter);
		ListView<E> clv = new ListView<E>();
		clv.setData(qr.getResultList());
		clv.setTotalRecord(qr.getTotalCount());
		writeJSON(response, clv);
	}

	@RequestMapping(value = "/save")
	public void doSave(E entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		Date now = new Date();
		if (CMD_EDIT.equals(parameter.getCmd())) {
			beforeSaveUpdate(entity);
			try {
				BeanUtils.setProperty(entity, "updateTime", now);
			} catch (Exception e) {
				e.printStackTrace();
			}
			service.update(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			beforeSaveNew(entity);
			try {
				BeanUtils.setProperty(entity, "createTime", now);
				BeanUtils.setProperty(entity, "updateTime", now);
			} catch (Exception e) {
				e.printStackTrace();
			}
			service.persist(entity);
		}
		parameter.setCmd(CMD_EDIT);
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	@RequestMapping(value = "/content/{id}")
	public void doContent(@PathVariable("id") Serializable id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Class<E> c = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Class returnClass = null;
		try {
			returnClass = c.getMethod("get" + idField.substring(0, 1).toUpperCase() + idField.substring(1), new Class[] {}).getReturnType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		E entity = service.get((Serializable) ConvertUtils.convert(id, returnClass));
		entity.setSuccess(true);
		entity.setCmd(CMD_EDIT);
		writeJSON(response, entity);
	}

	@RequestMapping(value = "/delete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Serializable[] ids) throws IOException {
		service.updateByProperties(idField, ids, new String[] { statusField }, new Object[] { -1 });
		writeJSON(response, "{success:true}");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
	}

	protected void writeJSON(HttpServletResponse response, String json) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}

	protected void writeJSON(HttpServletResponse response, Object obj) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		responseJsonGenerator.writeObject(obj);
	}

	protected void beforeSaveNew(E example) {

	}

	protected void beforeSaveUpdate(E example) {

	}

	protected void beforeList(E example) {

	}

}
