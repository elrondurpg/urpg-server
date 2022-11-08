package com.pokemonurpg.configuration.v1.lib.view;

public class ConfigurationViews {
    public static interface V1 {
        public static interface Pokemon extends V1 {
            public static interface Ability extends V1 {
                public static interface Id extends Species {

                }
                public static interface Brief extends Id {

                }
                public static interface Full extends Brief {

                }
            }
            public static interface Nature extends V1 {

            }
            public static interface Species extends V1 {
                public static interface Id extends Species {

                }
                public static interface Brief extends Id {

                }
                public static interface Full extends Brief {

                }
            }
            public static interface Type extends V1 {

            }
        }
    }
}
