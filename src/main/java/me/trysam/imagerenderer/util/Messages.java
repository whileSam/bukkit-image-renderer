package me.trysam.imagerenderer.util;

public class Messages {

    public static final String PREFIX = "§8[§dImageParticles§8]§r ";

    public static final String CREATING_IMG_DIRECTORY = PREFIX+"§aCouldn't find §eimg§a directory. Creating a new one.";

    public static final String ENABLED = PREFIX+"§aPlugin successfully enabled.";
    public static final String DISABLED = PREFIX+"§cPlugin successfully disabled.";

    public static final String MUST_BE_PLAYER = PREFIX+"§cCommand executor must be a player!";
    public static final String NO_PERMISSIONS = PREFIX+"§cYou are not permitted to use this command!";
    public static final String INVALID_SYNTAX = PREFIX+"§cInvalid Syntax! Error in argument {0}";
    public static final String USAGE = PREFIX+"§7Usage§8: §e/{0}";
    public static final String ERROR = PREFIX+"§cSomething went wrong. Check your input!";
    public static final String COULD_NOT_FIND_IMAGE = PREFIX+"§cCould not find image!";
    public static final String WRONG_IMAGE_DIMENSIONS = PREFIX+"§cImage pixel sum must be less than or equal 64^2!";
    public static final String SUCCESS = PREFIX+"§aSuccessfully rendered image.";

    /**
     * Replaces all {n} placeholders substitute arguments.<br>
     * {0} refers to the substitutes array at index 0.<br>
     * If there are {n} bigger than the highest substitutes index,
     * the {n} will just be ignored
     * @param input The string where the substitutes should go.
     * @param substitutes The substitute array.
     * @return input, all {n} replaced with substitutes
     */
    public static String replace(String input, String... substitutes) {
        for (int i = 0; i < substitutes.length; i++) {
            input = input.replace("{"+i+"}", substitutes[i]);
        }
        return input;
    }

}
