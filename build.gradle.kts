val someAttribute = Attribute.of("someAttribute", String::class.java)

val variantName = configurations.consumable("variantName") {
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

val stuff = configurations.dependencyScope("stuff")
val stuffResolved = configurations.resolvable("stuffResolved") {
    extendsFrom(stuff.get())
    attributes {
        attribute(someAttribute, "gizmo")
    }
}

dependencies {
    add("stuff", project(":"))
}

tasks.register("resolveStuff") {
    dependsOn(stuffResolved.get())
}



