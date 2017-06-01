/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package content;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.PasswordPolicy;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class EmployeeDirectoryModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterCreate(User model) throws ModelListenerException {
		
		List<PasswordPolicy> passwordPolicies = PasswordPolicyLocalServiceUtil.getPasswordPolicies(0,
				PasswordPolicyLocalServiceUtil.getPasswordPoliciesCount());
		PasswordPolicy passwordPolicy = null;
		for (int i = 0; i < passwordPolicies.size(); i++) {
			if (passwordPolicies.get(i).getName().equals("SSi Password Policy")) {
				passwordPolicy = passwordPolicies.get(i);
			}
		}
		if (passwordPolicy != null) {
			UserLocalServiceUtil.addPasswordPolicyUsers(passwordPolicy.getPasswordPolicyId(),
					new long[] { model.getUserId() });
		}
		super.onAfterCreate(model);
	}
}