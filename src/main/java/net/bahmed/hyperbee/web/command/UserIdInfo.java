package net.bahmed.hyperbee.web.command;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author azim
 * @since 11/24/16
 */
public class UserIdInfo {

    @Size(min = 1)
    List<Integer> userIdList;

    public UserIdInfo() {
        userIdList = new ArrayList<Integer>();
    }

    public UserIdInfo(List<Integer> idList) {
        userIdList = idList;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    public String toString() {
        return Arrays.deepToString(userIdList.toArray());
    }
}
