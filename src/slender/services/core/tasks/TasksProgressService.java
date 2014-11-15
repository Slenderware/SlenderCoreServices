/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.tasks;

/**
 *
 * @author Heinrich
 */
public interface TasksProgressService {
    public int getProgress(Integer taskId);
    public double getProgressPercentage(Integer taskId);
    public void addProgress(Integer taskId, Integer userId, int hours);
    public boolean markAsComplete(Integer taskId);
}
