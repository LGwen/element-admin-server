package com.lgwen.mybatisredismysql.controller;

import com.lgwen.mybatisredismysql.annotation.UserLoginToken;
import com.lgwen.mybatisredismysql.beans.Right;
import com.lgwen.mybatisredismysql.beans.User;
import com.lgwen.mybatisredismysql.beans.UserInfo;
import com.lgwen.mybatisredismysql.config.JSONResult;
import com.lgwen.mybatisredismysql.service.RightService;
import com.lgwen.mybatisredismysql.service.TokenService;
import com.lgwen.mybatisredismysql.service.TokenUtils;
import com.lgwen.mybatisredismysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RightService rightService;
    @Autowired
    TokenService tokenService;
    @Autowired
    TokenUtils tokenUtils;

    @GetMapping("/user/{id}")
    public JSONResult<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        return new JSONResult<User>("200","成功", user);
    }
    @UserLoginToken
    @GetMapping("/user/all")
    public JSONResult<List<User>> findAll(){
        List<User> userList = userService.findAll();
        return new JSONResult<List<User>>("200","成功",userList);
    }

    @UserLoginToken
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public JSONResult addUser(@RequestBody User user) {
        userService.addUser(user);
        return new JSONResult("200", "成功", user);
    }
    @UserLoginToken
    @RequestMapping(value = "/user/delete",method = RequestMethod.POST)
    public JSONResult deleteUser(@RequestBody User user) {
        System.out.println("删除了:"+user.getId()+"号用户");
        userService.deleteUserById(user.getId());
        return new JSONResult("200", "成功", user);
    }
    // 
//    @GetMapping("login")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestBody User user, HttpServletResponse response){
        User userBase = new User();
        System.out.println("user:"+user);
        User sqlUser = userService.findUserByName(user);
        if(sqlUser==null){
            return  new  JSONResult("200","用户不存在","");
        }
        System.out.println("sqlUser:"+user);
        userBase.setId(sqlUser.getId());
        userBase.setPassword(sqlUser.getPassword());
        userBase.setUsername(sqlUser.getUsername());
        if(userBase.getPassword().equals(user.getPassword())){
            String Token = tokenService.getToken(userBase);
            Cookie cookie = new Cookie("token",Token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return  new  JSONResult("200","登录成功",Token);
        }else {
            return  new  JSONResult("200","账号或密码错误","");
        }
    }
    @UserLoginToken
    @RequestMapping(value = "/getmessage",method = RequestMethod.POST)
    public JSONResult<Object> testToken() {
        String id = tokenUtils.getTokenUserId();
        return new JSONResult("200", "token验证成功", id);

    }
    
    /**
     * 更新用户信息
     * user
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    public JSONResult updateUserInfo(@RequestBody User user){
        userService.updateUserById(user);
        return new JSONResult<UserInfo>("200","成功", null);
    }

    @UserLoginToken
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public JSONResult getUserInfo(){
        String id = tokenUtils.getTokenUserId();

        System.out.println("userid:"+id);
        User user = userService.getUser(Integer.parseInt(id));
        System.out.println("user:"+user);

        Right right = rightService.getUserRight(Integer.parseInt(id));
        String rightes;
        List<String> rr = null;
        if(right!=null){
            rightes = right.getRightids();
            rr = Arrays.asList(rightes.split(","));
        }
        UserInfo userInfo = new UserInfo(user,rr);
        return new JSONResult<UserInfo>("200","成功", userInfo);
    }

}
