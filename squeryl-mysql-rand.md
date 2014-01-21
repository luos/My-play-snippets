Squeryl  Mysql Random custom function example
=========================

Put this in your AppPrimitive class:
Only tested for ordering.

````

		class MysqlRand(m:OutMapper[Double])
  extends FunctionNode[Double]("RAND", Some(m), Seq()) with NumericalExpression[Double]{

  }

   	def mysqlRand()(implicit m:OutMapper[Double]) = new MysqlRand(m)
	}
	
````

usage example:
````
from(Submission.table)((s) => 
where(s.is_deleted === false and s.channel_id === this.channel_id) 
select(s)  orderBy(mysqlRand()) ).page(0,5)
````
