package io.soen487p3.webservice.covid19tracker.controller;


import java.io.IOException;
import java.util.Map;

import io.soen487p3.webservice.covid19tracker.model.AffectedCountries;
import io.soen487p3.webservice.covid19tracker.model.CountriesStats;
import io.soen487p3.webservice.covid19tracker.model.CountryStats;
import io.soen487p3.webservice.covid19tracker.model.User;
import io.soen487p3.webservice.covid19tracker.model.WorldStats;
import io.soen487p3.webservice.covid19tracker.service.UserService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;

@Controller
public class AuthenticationController {
    private final String LOGIN = "login";
    private final String REGISTER = "register";
    private final String USER = "user";
    private final String HOME = "home";
    private final String WORLD_STA = "worldsta";
    private final String AFFECTED_COUNTRIES = "affectedcountries";
    private final String CANADA = "canada";
    private final String CANADA_DETAIL = "canadaDetail";
    private final String COUNTRY_STA = "countrysta";
    private final String SUCCESS_MSG = "successMessage";
   @Autowired
   UserService userService;
   @Autowired
   CovidInfoController covidInfoController;

   @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
   public ModelAndView login() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName(LOGIN); // resources/template/login.html
      return modelAndView;
   }

   @RequestMapping(value = "/register", method = RequestMethod.GET)
   public ModelAndView register() {
      ModelAndView modelAndView = new ModelAndView();
      User user = new User();
      modelAndView.addObject(USER, user);
      modelAndView.setViewName(REGISTER); // resources/template/register.html
      return modelAndView;
   }

   @RequestMapping(value = {"/"}, method = RequestMethod.GET)
   public ModelAndView index() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("index"); // resources/template/index.html
      return modelAndView;
   }

   @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
   public ModelAndView allHome() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName(HOME); // resources/template/home.html
      return modelAndView;
   }

   @RequestMapping(value = "/admin", method = RequestMethod.GET)
   public ModelAndView adminHome() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("admin"); // resources/template/admin.html
      return modelAndView;
   }

   @RequestMapping(value = "/user", method = RequestMethod.GET)
   public ModelAndView userHome() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName(USER); // resources/template/admin.html
      return modelAndView;
   }

   @RequestMapping(value = "/superuser", method = RequestMethod.GET)
   public ModelAndView superuserHome() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("superuser"); // resources/template/admin.html
      return modelAndView;
   }

   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
      ModelAndView modelAndView = new ModelAndView();
      // Check for the validations
      if (bindingResult.hasErrors()) {
         modelAndView.addObject(SUCCESS_MSG, "Please correct the errors in form!");
         modelMap.addAttribute("bindingResult", bindingResult);
         modelAndView.setViewName(REGISTER);
      } else if (userService.isUserAlreadyPresent(user)) {
         modelAndView.addObject(SUCCESS_MSG, "user already exists!");
         modelAndView.setViewName(REGISTER);
      }
      // we will save the user if, no binding errors
      else {
         userService.saveUser(user);
         modelAndView.addObject(SUCCESS_MSG, "User is registered successfully, please login.");
         modelAndView.setViewName(LOGIN);
      }
      modelAndView.addObject(USER, new User());
      return modelAndView;
   }

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public ModelAndView userLogin(BindingResult bindingResult, ModelMap modelMap) {
      ModelAndView modelAndView = new ModelAndView();
      // Check for the validations
      if (bindingResult.hasErrors()) {
         modelAndView.addObject(SUCCESS_MSG, "Please correct the errors in form!");
         modelAndView.setViewName(LOGIN);
      } else {
         modelAndView.setViewName(HOME);
      }
      return modelAndView;
   }

   @RequestMapping(value = "/worldsta", method = RequestMethod.GET)
   public ModelAndView worldSta() {
      ModelAndView modelAndView = new ModelAndView();
      try {
         WorldStats worldStats = covidInfoController.getWorldStats();
         modelAndView.setViewName(WORLD_STA); // resources/template/worldSta.html
         modelAndView.addObject(WORLD_STA, worldStats);
      } catch (IOException | JSONException e) {
         e.printStackTrace();
      }
      return modelAndView;
   }

   @RequestMapping(value = "/affectedcountries", method = RequestMethod.GET)
   public ModelAndView affectedCountries() {
      ModelAndView modelAndView = new ModelAndView();
      try {
         AffectedCountries affectedCountries = covidInfoController.getAffectedCountries();
         modelAndView.setViewName(AFFECTED_COUNTRIES); // resources/template/worldSta.html
         modelAndView.addObject(AFFECTED_COUNTRIES, affectedCountries);
         return modelAndView;
      } catch (IOException | JSONException e) {
         e.printStackTrace();
      }
      return modelAndView;
   }

   @RequestMapping(value = "/canada", method = RequestMethod.GET)
   public ModelAndView countrySta() {
      ModelAndView modelAndView = new ModelAndView();
      try {
         CountryStats canadaStats = covidInfoController.getCanadaStatsSmall();
         Map canadaDetailInfo = (JSON.parseObject(covidInfoController.getCanadaStats()).getJSONObject("data"));
         modelAndView.setViewName(CANADA); // resources/template/worldSta.html
         modelAndView.addObject(CANADA, canadaStats);
         modelAndView.addObject(CANADA_DETAIL, canadaDetailInfo);
         return modelAndView;
      } catch (IOException | JSONException e) {
         e.printStackTrace();
      }
      return modelAndView;
   }

}