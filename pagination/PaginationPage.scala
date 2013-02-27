package luos{
import org.squeryl.PrimitiveTypeMode._ 
import org.squeryl.Query
import org.squeryl.KeyedEntity
import play.api.mvc._;

	class PaginationPage[T](val query: Query[T])(implicit val request: RequestHeader){
		lazy val page : Int = { 
			val a = request.queryString.get(pid + "page").flatMap(_.headOption)
			try { 
				a.get.toInt
			} catch {
			  case _ :Throwable => 0
			}
		}
		var pid = "pagination"
		var pageLimit = 15;
		var url = request.path
		lazy val recordCount: Int= 
			inTransaction{ 
				val res :Long = (from(query)((q) => compute(count())))
					//res.asInstanceOf[Int]
					res.asInstanceOf[Int]
				}
		lazy val data: List[T] = inTransaction{ 
			query.page(page*pageLimit,pageLimit).toList
		}


		def render() = {
			<div class="pagination pagination-centered">
	             <ul>
	       		{for( i <- 0 to recordCount/pageLimit) yield <li><a href={url+"?"+pid+"page="+i}>{i+1}</a></li>}
	             </ul>
	          </div>
		}
	}



}