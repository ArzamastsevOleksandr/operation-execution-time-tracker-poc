package poc.operationexecutiontimetracker.util;

import java.util.UUID;

public class TrackerContext {

    static final ThreadLocal<UUID> OPERATION_UUID = InheritableThreadLocal.withInitial(UUID::randomUUID);
    static final ThreadLocal<Boolean> CLEAR_CONTEXT = InheritableThreadLocal.withInitial(() -> true);
    static final ThreadLocal<String> TAG = new InheritableThreadLocal<>();

    private TrackerContext() {
    }

    public static void setOperationId(UUID operationUuid) {
        OPERATION_UUID.set(operationUuid);
    }

    public static void setClearContext(boolean clearContext) {
        CLEAR_CONTEXT.set(clearContext);
    }

    public static void setTag(String tag) {
        TAG.set(tag);
    }

    public static UUID operationId() {
        return OPERATION_UUID.get();
    }

    public static Boolean clearContext() {
        return CLEAR_CONTEXT.get();
    }

    public static String tag() {
        return TAG.get();
    }

    public static void clear() {
        OPERATION_UUID.remove();
        CLEAR_CONTEXT.remove();
        TAG.remove();
    }

}
