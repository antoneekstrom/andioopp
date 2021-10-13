package andioopp.model.player;

/**
 * Indicates that {@link Money#spend(Money)} was called with an invalid cost.
 * This would be caused by attempting to spend negative money or more money than there is available.
 * @author Anton Ekström
 */
public class SpendMoneyException extends RuntimeException {
    public SpendMoneyException() {
        super("Could not spend money.");
    }
}
