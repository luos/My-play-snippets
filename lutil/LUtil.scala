package luos { 
	object LUtil {
		 def slugify(str: String): String = {
		    import java.text.Normalizer
		    Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\w ]", "").replace(" ", "-").toLowerCase
		  }
	}
}