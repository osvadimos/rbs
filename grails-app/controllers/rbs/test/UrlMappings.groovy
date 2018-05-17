package rbs.test

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/primes/$number"(controller: 'index', action:"index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
