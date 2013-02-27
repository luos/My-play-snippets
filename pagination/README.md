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