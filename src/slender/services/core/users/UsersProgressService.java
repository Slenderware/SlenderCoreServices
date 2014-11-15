/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.users;

/**
 *
 * @author Heinrich
 */
public interface UsersProgressService {
    public int getTimeSpentForTask(String sessionId, Integer taskId);
    public int getTimeSpentForProject(String sessionId, Integer projectId);
    public double getPercentageSpentForTask(String sessionId, Integer taskId);
    public double getPercentageSpentForProject(String sessionId, Integer projectId);
}
