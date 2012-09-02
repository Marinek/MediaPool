package de.mediapool.core.persistence.core;

import java.util.ArrayList;
import java.util.List;

import com.mysema.query.sql.SQLSerializer;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.Constant;
import com.mysema.query.types.Path;


public final class PSHibernateSQLSerializer extends SQLSerializer{

    private final List<Path<?>> entityPaths = new ArrayList<Path<?>>();

    public PSHibernateSQLSerializer(SQLTemplates templates) {
        super(templates);
    }

    @Override
    public void visit(Constant<?> expr) {
        if (!getConstantToLabel().containsKey(expr.getConstant())) {
            String constLabel = getConstantPrefix() + (getConstantToLabel().size() + 1);
            getConstantToLabel().put(expr.getConstant(), constLabel);
            append(":"+constLabel);
        } else {
            append(":"+getConstantToLabel().get(expr.getConstant()));
        }
    }

    @Override
    public void visit(Path<?> path) {
        if (path.getMetadata().getParent() == null && !path.getType().equals(path.getClass())){
            super.visit(path);
        }else{
            super.visit(path);
        }
    }

    public List<Path<?>> getEntityPaths() {
        return entityPaths;
    }

}
