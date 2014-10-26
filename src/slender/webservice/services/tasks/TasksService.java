/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.tasks;

import com.slender.domain.Task;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface TasksService {
    public List<Task> getTasks(Integer projectId);
    public int getProgress(Integer taskId);
}
