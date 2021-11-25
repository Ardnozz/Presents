package cz.ardno.presents.utilities;

public enum MessageUtility {

    CMD_EXECUTOR_NOT_PLAYER("§cCommand have to be executed by Player!"),
    CMD_PRESENTGIVE_SEND_THEMSELVES("§cYou cannot send a present to yourself."),
    CMD_PRESENTGIVE_ARGUMENT_OFFLINE_PLAYER("§cYou cannot send the present to offline player!"),
    CMD_PRESENTGIVE_HOLDING_WRONG_ITEM("§cYou have to hold not given present yet!"),
    CMD_GIVEPRESENT_SUCCESSFULLY_SENDED("§aPresent has been successfully sended."),
    CMD_CLAIMPRESENTS_ANY_PRESENTS("§cYou don't have any presents :("),
    CMD_CLAIMPRESENTS_SUCCESSFULLY("§aYou have successfully received the presents."),
    CMD_PRESENTADMIN_FULL_INV("§cYou have full inventory!"),
    CMD_PRESENTADMIN_ITEM_DOESNT_EXIST("§cItem with that id doesn't exist."),
    CMD_PRESENTADMIN_SUCCESS("§cItem with that id doesn't exist."),

    PRESENT_GIVEN("§aSomeone gave you a present. Claim it with /claimpresents."),
    PRESENT_DOESNT_EXIST("§7The present doesn't exist. Replacing the item with a new present."),
    CANNOT_RENAME_HEAD("§cYou cannot rename a player head."),
    FULL_CRAFTING("§cThe crafting is full. You have to empty the result slot."),
    CMD_ARGUMENT_NEEDED("§cYou have to set the first argument (%s)!");

    private final String message;

    MessageUtility(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
