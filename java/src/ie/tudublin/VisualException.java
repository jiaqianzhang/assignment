<<<<<<< HEAD
package ie.tudublin;

public class VisualException extends Throwable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
   
    private String message;
   
    public VisualException(String message)
    {
        this.message = message;
    }
    public String toString()
    {
        return message;
    }
=======
package ie.tudublin;

public class VisualException extends Throwable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String message;
    
    public VisualException(String message)
    {
        this.message = message;
    }

    public String toString()
    {
        return message;
    }
>>>>>>> cde7e0c8fb7a7ce2bf7a7cfe74e8b88254a4d529
}