package ch.magdenbt.a1ccaclient.utils

 abstract class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
     abstract fun copyWithFilteredData(filter : (T?) -> T):  Resource<T>
     class Success<T>(data: T) : Resource<T>(data){
        override fun copyWithFilteredData(filter : (T?) -> T): Success<T> {


            val filteredData = filter(data)
            return Success(filteredData)
        }
    }


    class Loading<T>(data: T? = null) : Resource<T>(data){

        override fun copyWithFilteredData(filter : (T?) -> T): Loading<T> {
            val filteredData = filter(data)
            return Loading(filteredData)
        }

    }
    class Error<T>(throwable: Throwable, data: T? = null): Resource<T>(data, throwable){

        override fun copyWithFilteredData(filter : (T?) -> T): Error<T> {
            val filteredData = filter(data)
            return Error(error!!,filteredData)
        }

    }


}