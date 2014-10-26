/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.accounts;

import com.slender.domain.Company;
import com.slender.domain.Users;

/**
 *
 * @author Heinrich
 */
public interface RegistrationService {
    public Users addUser();
    public Company addCompany();
}
