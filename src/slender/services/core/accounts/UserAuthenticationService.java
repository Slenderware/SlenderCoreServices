/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.accounts;

import com.slender.domain.Users;

/**
 *
 * @author Heinrich
 */
public interface UserAuthenticationService {
    public int authenticate(String username, String password);
}   