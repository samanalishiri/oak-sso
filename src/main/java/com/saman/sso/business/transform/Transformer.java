package com.saman.sso.business.transform;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.saman.sso.util.CollectionUtils.EMPTY_LIST;

/**
 * Created by saman on 10/22/2017.
 */
public abstract class Transformer<I extends Serializable, E extends AbstractAuditingEntity<I, String>, M extends AbstractModel<I>> {

    public static final int ZERO_DEEP = -1;
    public static final int EXIT = 0;

    @Autowired
    protected MessageSource messageSource;

    public abstract Class<? extends AbstractModel> getModel();

    public abstract Class<? extends AbstractAuditingEntity> getEntity();

    public void beforeTransformFromEntityToModel(E e, int deep, String... relations) {
        Objects.requireNonNull(e, "");
    }

    public abstract void transformFromEntityToModel(E input, M output, int deep, String... relations);

    public void afterTransformFromEntityToModel(E e, M m, int deep, String... relations) {
    }

    public M transform(E e, int deep, String... relations) {
        M m = newModel();

        beforeTransformFromEntityToModel(e, deep, relations);
        transformFromEntityToModel(e, m, deep, relations);
        afterTransformFromEntityToModel(e, m, deep, relations);

        return m;
    }

    public M transform(E e, String... relations) {
        return transform(e, ZERO_DEEP, relations);
    }

    public M[] transform(E[] entities, int deep, String... relations) {
        return CollectionUtils.isEmpty(entities)
                ? (M[]) Array.newInstance(getModel(), 0)
                : Arrays.stream(entities).map(e -> transform(e, deep, relations)).toArray(size -> (M[]) Array.newInstance(getModel(), size));
    }

    public List<M> transformFromEntitiesToModels(Supplier<List<E>> entities, int deep, String... relations) {
        return CollectionUtils.isEmpty(entities.get())
                ? EMPTY_LIST
                : entities.get().stream().map(e -> transform(e, deep, relations)).collect(Collectors.toList());
    }

    public void beforeTransformFromModelToEntity(M m, int deep, String... relations) {
        Objects.requireNonNull(m, "");
    }

    public abstract void transformFromModelToEntity(M input, E output, int deep, String... relations);

    public void afterTransformFromModelToEntity(M m, E e, int deep, String... relations) {
    }

    public E transform(M m, int deep, String... relations) {
        return transform(m, newEntity(), deep, relations);
    }

    public E transform(M m, E e, int deep, String... relations) {

        beforeTransformFromModelToEntity(m, deep, relations);
        transformFromModelToEntity(m, e, deep, relations);
        afterTransformFromModelToEntity(m, e, deep, relations);

        return e;
    }

    public E transform(M m, String... relations) {
        return transform(m, ZERO_DEEP, relations);
    }

    public E[] transform(M[] models, int deep, String... relations) {
        return CollectionUtils.isEmpty(models)
                ? (E[]) Array.newInstance(getModel(), 0)
                : Arrays.stream(models).map(e -> transform(e, deep, relations)).toArray(size -> (E[]) Array.newInstance(getModel(), size));
    }

    public List<E> transformFromModelsToEntities(Supplier<List<M>> models, int deep, String... relations) {
        return CollectionUtils.isEmpty(models.get())
                ? EMPTY_LIST
                : models.get().stream().map(m -> transform(m, deep, relations)).collect(Collectors.toList());

    }

    public abstract E newEntity();

    public abstract M newModel();
}
