/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.company.impl;

import com.slender.domain.Company;
import com.slender.domain.UserProject;
import com.slender.domain.Users;
import com.slender.service.crud.CompanyCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.impl.CompanyCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.services.core.company.CompanyService;

/**
 *
 * @author Guest Account
 */
public class CompanyServiceImpl implements CompanyService {

    /**
     * Add new Company
     * 
     * @param company   Company entity object
     * @return          ID populated company
     */
    @Override
    public Company addCompany(Company company) {
        CompanyCrud crud = new CompanyCrudImpl();
        return crud.persist(company);
    }

    /**
     * Return a list of users for a company
     * 
     * @param companyId ID of the company
     * @return          List of users
     */
    @Override
    public List<Users> getCompanyUsers(Integer companyId) {
        UserCrud crud = new UserCrudImpl();
        return crud.getEntitiesByProperName("companyId", companyId);
    }
    
}
