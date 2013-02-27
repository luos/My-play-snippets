My-play-snippets
================

My Play Framework 2.0 , 2.1 snippets

This repo will contain some (i think) useful classes and methods for scala/play. 

I'm not a master scala programmer nor a functional programmer so these classes could contain some "not-so-functional" lines too. Sorry for that. 

I'm writing my first play application in my free time and I was surprised there is not so much code out there for it, for example I could'nt find a single pagination snippet that could paginate squeryl snippets, I searched a lot for a scala style slugify method and so on. 

I use play 2.1 with squeryl. 


TODO: Look for some method to include these extensions to play as simple as possible, maybe as a modul or plugin or project. 

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

and add "filters" as dependency in your Build.scala like this (you don't have to add the others)

```
  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "mysql" % "mysql-connector-java" % "5.1.18",
    "org.squeryl" % "squeryl_2.10" % "0.9.5-6",
    filters,
    "be.objectify" %% "deadbolt-core" % "2.1-SNAPSHOT",
    javaJpa
  )

```


This only works in 2.1 because the filters became available in this version.

(Look at how CSRFFilter works, you can find some truly *good* pieces of code because of immutation)

Luos's Utils
------------

This object has (will have) some useful methods, currently only one. 

### Slugify

Removes accents and other stuff from string

Pagination
-------------

This is a simple pagination snippet for play squeryl. 

Usage:
In the controller you have to provide at least an implicit request, and pass it to your view

```
	val page = new PaginationPage[(Submission,User,Channel)](
			join(Submission,User.table, Channel.table)((s,u,c) =>  
       		 select(s,u,c)
        		on( s.user_id === u.id, s.channel_id === c.id )
      		))(request){
			
		};
```

In the view, you can call

```
	<ul>
	@for(elem <- page.data){
		<li>@elem.name</li>
	}

	</ul>
	This will render the pagenumbers:
	@page.render

```

,