package top.lrshuai.doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import top.lrshuai.doc.controller.base.BaseController;
import top.lrshuai.doc.service.IndexService;
import top.lrshuai.doc.util.Const;
import top.lrshuai.doc.util.ParameterMap;

@Controller
public class IndexController extends BaseController{

	@Value("${doc.name}")
	private String docName;

	@Autowired
	private IndexService indexService;
	
	@GetMapping(value={"/","/index"})
	public String index(Model model){
		ParameterMap pm = this.getParameterMap();
		model.addAttribute("menus", indexService.getDocs(pm));
		model.addAttribute("keyword",pm.getString("keyword"));
		model.addAttribute("docName",docName);
		return "index";
	}
	
	@GetMapping("/getParentDoc")
	@ResponseBody
	public Object getParentDoc(){
		return indexService.getParentDoc(this.getParameterMap());
	}
	
	@GetMapping("/toLogin")
	public String toLogin(){
		
		return "login";
	}
	
	@GetMapping("/toRegister")
	public String toRegister(){
		
		return "register";
	}
	
	@GetMapping("/logout")
	public String logout(){
		getSession().removeAttribute(Const.USER_SESSION);
		return "login";
	}
	
	@GetMapping("/templates/{pageName}")
	public String templatesPage(@PathVariable("pageName")String pageName){
		
		return "page/"+pageName;
	}
}
	
	
