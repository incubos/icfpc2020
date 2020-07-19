package icfpc2020.api;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public abstract class API {
    @NotNull
    public abstract String send(@NotNull String body);
}
