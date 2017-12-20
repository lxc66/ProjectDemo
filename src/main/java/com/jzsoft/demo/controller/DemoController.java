package com.jzsoft.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.demo.model.TBUser;
import com.jzsoft.platform.core.cache.CacheHelper;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.util.tree.TreeNode;
import com.jzsoft.platform.util.tree.TreeUtil;
import com.jzsoft.redis.JedisTemplate;

@Controller
@RequestMapping("/dm")
public class DemoController extends SpringController {
	@RequestMapping("/test")
	public String test() {
//		RedisUtil redisUtil = new RedisUtil();
//		redisUtil.addSet("k1", 123);
//		System.out.println(redisUtil.getValue("k1"));
		JedisTemplate template = new JedisTemplate();
		template.set("h", "123");
		return "/demo/test";
	}
	
	@RequestMapping("/list")
	public String list() {
		return "/ddos/list";
	}
	
	@RequestMapping("/input")
	public String input() {
		return "/ddos/input";
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2(@RequestParam(value = "a") String a) {
		return a;
	}

	@RequestMapping("/test3")
	@ResponseBody
	public String test3(String a, HttpServletResponse res) {
		return "OK:test3" + a;
	}

	@RequestMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") TBUser user, Model model) {
		// model.addAttribute("user", user);
		user.setName("aaaaaaaaaa");
		return "demo/user";
	}
	
	@RequestMapping("/getMenuTreeJson")
	@ResponseBody
	public String getMenuTreeJson() {
		List<TreeNode> nodes = new ArrayList<TreeNode>(0);
		TreeNode root = new TreeNode();
		root.setName("网站");
		root.setIsParent(true);
		root.setOpen(true);
		root.setId("0");
		
		TreeNode node1 = new TreeNode();
		node1.setId("1");
		node1.setName("搜索");
		node1.setOpen(true);
		node1.setParentId("0");
		
		TreeNode node11 = new TreeNode();
		node11.setId("11");
		node11.setName("百度");
		node11.setUrl("http://www.baidu.com");
		node11.setParentId("1");
		TreeNode node12 = new TreeNode();
		node12.setId("12");
		node12.setName("谷歌");
		node12.setUrl("http://www.google.cn");
		node12.setParentId("1");
		
		TreeNode node2 = new TreeNode();
		node2.setId("2");
		node2.setName("购物");
		node2.setParentId("0");
		TreeNode node21 = new TreeNode();
		node21.setId("21");
		node21.setName("淘宝");
		node21.setUrl("http://www.taobao.com");
		node21.setParentId("2");
		TreeNode node22 = new TreeNode();
		node22.setId("22");
		node22.setName("京东");
		node22.setUrl("http://www.jd.com");
		node22.setParentId("2");
		
		nodes.add(root);
		nodes.add(node1);
		nodes.add(node11);
		nodes.add(node12);
		nodes.add(node2);
		nodes.add(node21);
		nodes.add(node22);
		String treeJson = TreeUtil.buildTreeByParent(nodes);
		System.out.println(treeJson);
		return treeJson;
	}
	@RequestMapping("/testVideoConvertCallBack")
	@ResponseBody
	public void testVideoConvertCallBack(String status,String id,String path) {
		
		System.out.println("------收到视频转换后回调");
		System.out.println("status:"+status);
		System.out.println("id:"+id);
		System.out.println("path:"+path);
	}
	
	@RequestMapping("/testRedis")
	@ResponseBody
	public void testRedis() {
		System.out.println("------测试Redis缓存");
		CacheHelper.set("test1", "haha_##");
		CacheHelper.set("test2", "hoho_~~");
		CacheHelper.set("test3", "hehe_%%");
	}
	
	@RequestMapping("/testPrint")
	@ResponseBody
	public void testPrint() {
		this.print("test");
	}
	
}
