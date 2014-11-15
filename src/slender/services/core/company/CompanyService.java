/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.company;

import com.slender.domain.Company;
import com.slender.domain.Users;
import java.util.List;

/**
 *
 * @author Guest Account
 */
public interface CompanyService {
    public Company addCompany(Company company);
    public List<Users> getCompanyUsers(Integer companyId);
}
