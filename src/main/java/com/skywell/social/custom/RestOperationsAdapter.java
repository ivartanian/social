/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.skywell.social.custom;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.web.client.RestOperations;

import java.util.Map;

@SuppressWarnings({"unchecked"})
public class RestOperationsAdapter implements ApiAdapter<RestOperations> {

	private String baseGraph = "https://graph.facebook.com/v2.5/";

	public boolean test(RestOperations restOperations) {
		try {
			restOperations.getForObject(baseGraph + "me", Map.class);
			return true;
		} catch (ApiException e) {
			return false;
		}
	}

	public void setConnectionValues(RestOperations restOperations, ConnectionValues values) {
		Map<String, String> map = restOperations.getForObject(baseGraph + "me?fields=id,name,email,link", Map.class);
		values.setProviderUserId(map.get("id"));
		values.setDisplayName(map.get("name"));
		values.setProfileUrl(map.get("link"));
		values.setImageUrl(baseGraph + map.get("id") + "/picture");
	}

	public UserProfile fetchUserProfile(RestOperations restOperations) {
		Map<String, String> map = restOperations.getForObject(baseGraph + "me?fields=id,name,email,first_name,last_name", Map.class);
		return new UserProfileBuilder().setName(map.get("name")).setFirstName(map.get("first_name")).setLastName(map.get("last_name")).
			setEmail(map.get("email")).build();
	}
	
	public void updateStatus(RestOperations restOperations, String message) {
		throw new UnsupportedOperationException();
	}

}
