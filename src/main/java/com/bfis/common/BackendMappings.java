package com.bfis.common;

public class BackendMappings {

		
		
		private static final String API = "/api";
		
		
		public class User{
			
			public static final String SIGN_UP = API+"/user/signup";
			public static final String CHANGE_PASSWORD = API+"/user/changePassword";
			public static final String RESET_PASSWORD = API+"/user/resetPassword";
			public static final String ALL = API+"/user/getAll";
			public static final String DELETE = API+"/user/delete";

		}
		
		public class Movie{
			
			public static final String BY_GENRE = API+"/movie/byGenre";
			public static final String ALL = API+"/movie/getAll";
			public static final String BY_TYPE = API+"/movie/byType";
			public static final String ADD = API+"/movie/add";
			public static final String REMOVE = API+"/movie/remove";

		}



}
