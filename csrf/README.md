Secure Form CSRF Snippet
---------------

This view snippet is for including csrf in a cleaner style to the view than ```@form(CSRF(action))```

You can use this like the normal helper, just write secureform instead of form, it includes the token in a hidden input not in the url like the built in version:
```
@secureform(action)

```

You have to use the CSRFFilter, extend your Global class like this:

```
object Global extends WithFilters(CSRFFilter()) with GlobalSettings {

```

and add dependencies in your Build.scala

```
  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "mysql" % "mysql-connector-java" % "5.1.18",
    "org.squeryl" % "squeryl_2.10" % "0.9.5-6",
    __filters__,
    "be.objectify" %% "deadbolt-core" % "2.1-SNAPSHOT",
    javaJpa
  )

```


This only works in 2.1 because the filters became available in this version.

(Look at how CSRFFilter works, you can find some truly *good* pieces of code because of immutation)