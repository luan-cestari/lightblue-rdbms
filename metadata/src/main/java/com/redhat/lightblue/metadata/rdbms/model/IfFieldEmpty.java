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
package com.redhat.lightblue.metadata.rdbms.model;

import com.redhat.lightblue.common.rdbms.RDBMSConstants;
import com.redhat.lightblue.metadata.parser.MetadataParser;
import com.redhat.lightblue.util.Path;

public class IfFieldEmpty extends If<If, If> {
    private Path field;

    public void setField(Path field) {
        this.field = field;
    }

    public Path getField() {
        return field;
    }

    @Override
    public <T> void convert(MetadataParser<T> p, Object lastArrayNode, T node) {
        if (field == null || field.isEmpty()) {
            throw com.redhat.lightblue.util.Error.get(RDBMSConstants.ERR_FIELD_REQUIRED, "No field informed");
        }
        T s = p.newNode();

        p.putString(s, "field", field.toString());

        if (lastArrayNode == null) {
            p.putObject(node, "fieldEmpty", s);
        } else {
            T iT = p.newNode();
            p.putObject(iT, "fieldEmpty", s);
            p.addObjectToArray(lastArrayNode, iT);
        }
    }

    @Override
    public <T> If parse(MetadataParser<T> p, T ifT) {
        If x = null;
        T pathEmpty = p.getObjectProperty(ifT, "fieldEmpty");
        if (pathEmpty != null) {
            x = new IfFieldEmpty();
            String pathString = p.getStringProperty(pathEmpty, "field");
            if (pathString == null || pathString.isEmpty()) {
                throw com.redhat.lightblue.util.Error.get(RDBMSConstants.ERR_FIELD_REQUIRED, "fieldEmpty: field not informed");
            }
            ((IfFieldEmpty) x).setField(new Path(pathString));
        }
        return x;
    }
}
