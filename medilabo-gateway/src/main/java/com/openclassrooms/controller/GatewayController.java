//package com.openclassrooms.controller;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@CrossOrigin
//public class GatewayController {
//        @RequestMapping(method = RequestMethod.POST, value = "/login")
//        public boolean login(@RequestBody User user) {
//            return user.getUsername().equals("admin") && user.getPassword().equals("medilabo");
//        }
//
////        @RequestMapping("/patients/**")
////        public Principal getPatients(HttpServletRequest request) {
////            String authToken = request.getHeader("Authorization")
////                    .substring("Basic".length()).trim();
////            return () ->  new String(Base64.getDecoder()
////                    .decode(authToken)).split(":")[0];
////        }
////        @RequestMapping("/notes/**")
////        public Principal getNotes(HttpServletRequest request) {
////            String authToken = request.getHeader("Authorization")
////                    .substring("Basic".length()).trim();
////            return () ->  new String(Base64.getDecoder()
////                    .decode(authToken)).split(":")[0];
////        }
////        @RequestMapping("/assessments/**")
////        public Principal getAssessments(HttpServletRequest request) {
////            String authToken = request.getHeader("Authorization")
////                    .substring("Basic".length()).trim();
////            return () ->  new String(Base64.getDecoder()
////                    .decode(authToken)).split(":")[0];
////        }
//}
