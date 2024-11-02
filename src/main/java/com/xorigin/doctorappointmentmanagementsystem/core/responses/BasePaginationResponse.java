package com.xorigin.doctorappointmentmanagementsystem.core.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Page;

public class BasePaginationResponse {

    private Integer prev;
    private Integer next;
    private Integer last;

    @JsonIgnore
    private final Page<?> page;

    public BasePaginationResponse(Page<?> page) {
        this.page = page;

        setPrev();
        setNext();
        setLast();
    }

    public Integer getPrev() {
        return prev;
    }

    public Integer getNext() {
        return next;
    }

    public Integer getLast() {
        return last;
    }

    public Page<?> getPage() {
        return page;
    }

    private boolean hasPrevious() {
        if (page != null)
            return page.hasPrevious() && page.getTotalElements() > page.getSize();
        return false;
    }

    private Integer getPageNumber() {
        if (page != null)
            return page.getNumber() + 1;
        return 0;
    }

    private void setPrev() {
        if (page != null)
            this.prev = hasPrevious() ? getPageNumber() - 1 : null;
    }

    private void setNext() {
        if (page != null)
            this.next = page.hasNext() ? getPageNumber() + 1 : null;
    }

    private void setLast() {
        if (page != null)
            this.last = page.getTotalPages();
    }

}
