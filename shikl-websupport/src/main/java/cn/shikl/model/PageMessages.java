package cn.shikl.model;

import java.util.HashMap;
import java.util.List;

import cn.shikl.data.criteria.Page;

public abstract class PageMessages<T, V> extends Messages {
    public PageMessages(String result, String... messages) {
        super(result, messages);
    }

    public static PageMessages success(String... message) {
        return new PageMessages("success", message) {
            @Override
            protected List entityListToVOList(List entityList) {
                return null;
            }
        };
    }

    public static PageMessages failure(String... message) {
        return new PageMessages("failure", message) {
            @Override
            protected List entityListToVOList(List entityList) {
                return null;
            }
        };
    }

    public void setData(Page page) {
        HashMap map = new HashMap(5);
        map.put("totalPages", Integer.valueOf(page.getTotalPages()));
        map.put("pageNumber", Integer.valueOf(page.getNumber()));
        map.put("pageSize", Integer.valueOf(page.getSize()));
        map.put("totalNumber", Integer.valueOf(page.getTotalElements()));
        map.put("content", entityListToVOList(page.getContent()));
        super.setData(map);
    }

    protected abstract List<V> entityListToVOList(List<T> entityList);
}