/*
 Copyright 2013 Red Hat, Inc. and/or its affiliates.

 This file is part of lightblue.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.redhat.lightblue.metadata.rdbms.converter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lcestari
 */
public class SelectStmt {
    private boolean distinct;
    private List<String> resultColumns = new ArrayList<>();
    private List<String> fromTables = new ArrayList<>();
    private LinkedList<String> whereConditionals = new LinkedList<>();
    private List<String> groupBy = new ArrayList<>(); // TODO Future implementation
    private List<String> having = new ArrayList<>(); // TODO Future implementation
    private List<String> orderBy = new ArrayList<>();
    private Range range;
    private Translator t;

    public SelectStmt(Translator t) {
        this.t = t;
    }

    public boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<String> getResultColumns() {
        return resultColumns;
    }

    public void setResultColumns(List<String> resultColumns) {
        this.resultColumns = resultColumns;
    }

    public List<String> getFromTables() {
        return fromTables;
    }

    public void setFromTables(List<String> fromTables) {
        this.fromTables = fromTables;
    }

    public LinkedList<String> getWhereConditionals() {
        return whereConditionals;
    }

    public void setWhereConditionals(LinkedList<String> whereConditionals) {
        this.whereConditionals = whereConditionals;
    }

    public List<String> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
    }

    public List<String> getHaving() {
        return having;
    }

    public void setHaving(List<String> having) {
        this.having = having;
    }

    public List<String> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
    }

    public String generateStatement(){
        return t.generateStatement(this);
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }
}
