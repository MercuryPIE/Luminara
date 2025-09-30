package Luminara.Core;

public enum PropertyManager {
    /// The current version form the properties file or -1 if the file is missing.
    APP_VERSION("APP_VERSION", "-1"),

    /// The current version form the properties file or luminara-output if the file is missing.
    DEFAULT_FILE_NAME("DEFAULT_FILE_NAME", "luminara-output"),

    /// The current version form the properties file or png if the file is missing.
    DEFAULT_FILE_FORMAT("DEFAULT_FILE_FORMAT", "png");

    private final String Key;
    private final String Default;

    PropertyManager(String Key, String Default) {
        this.Key = Key;
        this.Default = Default;
    }

    public String Key() { return Key; }
    public String Default() { return Default; }
}
