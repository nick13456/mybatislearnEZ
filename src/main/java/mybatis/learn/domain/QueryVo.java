package mybatis.learn.domain;

import java.util.ArrayList;
import java.util.List;

public class QueryVo {
    private User user;
    private List<Integer> idList;

    public QueryVo() {
    }

    public QueryVo(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
