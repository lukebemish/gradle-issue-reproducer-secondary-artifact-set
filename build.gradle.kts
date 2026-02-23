val someAttribute = Attribute.of("someAttribute", String::class.java)

val variantName by configurations.consumable {
    attributes {
        attribute(someAttribute, "gizmo")
    }
    outgoing.variants {
        register("secondaryArtifactSet") {
            attributes {
                attribute(someAttribute, "gadget")
            }
        }
    }
}

val stuff by configurations.dependencyScope
val stuffResolved by configurations.resolvable {
    extendsFrom(stuff)
    attributes {
        attribute(someAttribute, "gizmo")
    }
}

dependencies {
    add("stuff", project(":"))
}

tasks.register("resolveStuff") {
    dependsOn(stuffResolved)
}

