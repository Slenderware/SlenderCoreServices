/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.company.impl;

import com.slender.domain.Company;
import com.slender.service.crud.CompanyCrud;
import com.slender.service.crud.impl.CompanyCrudImpl;
import slender.services.core.company.CompanyService;

/**
 *
 * @author Guest Account
 */
public class CompanyServiceImpl implements CompanyService {

    @Override
    public Company addCompany(Company company) {
        CompanyCrud crud = new CompanyCrudImpl();
        return crud.persist(company);
    }
    
}
