package com.xorigin.doctorappointmentmanagementsystem.core.responses;

import org.springframework.data.domain.Page;

public class BasePaginationResponse {

    private Integer prev;
    private Integer next;
    private Integer last;
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

    private void setPrev() {
        if (page != null)
            this.prev = page.hasPrevious() ? page.getNumber() - 1 : null;
    }

    private void setNext() {
        if (page != null)
            this.next = page.hasNext() ? page.getNumber() + 1 : null;
    }

    private void setLast() {
        if (page != null)
            this.last = page.getTotalPages() - 1;
    }

}
