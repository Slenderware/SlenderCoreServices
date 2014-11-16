/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.company.impl;

import com.slender.domain.Company;
import com.slender.domain.Users;
import com.slender.service.crud.CompanyCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.impl.CompanyCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import java.util.List;
import slender.services.core.company.CompanyService;

/**
 * @author Heinrich van den Ende
 * 
 *  The MIT License (MIT)

    Copyright © 2014 Slenderware

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
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
