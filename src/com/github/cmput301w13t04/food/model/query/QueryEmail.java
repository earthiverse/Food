package com.github.cmput301w13t04.food.model.query;

/**
 * Used for the format for querying ElasticSearch (Simply copies the format)
 */
public class QueryEmail {
	public Query query;

	/**
	 * Create a new UserQuery object
	 * 
	 * @param query2
	 *            Username to query
	 */
	public QueryEmail(String query2) {
		query = new Query(query2);
	}

	public class Query {
		public StringQuery query_string;

		public Query(String query) {
			query_string = new StringQuery(query);
		}

		public class StringQuery {
			public String default_field = "recipe.author.email";
			public String query;

			public StringQuery(String query2) {
				query = query2;
			}
		}
	}
}
