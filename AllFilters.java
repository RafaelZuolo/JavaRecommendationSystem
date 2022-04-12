import java.util.Arrays;
import java.util.ArrayList;

/**
 *  Filter that combine one or more filters.
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 05/09/2021
 */

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    /**
     * Constructor of the filter. No parameter should be
     * passed. The filters are combined via the addFilter method
     * 
     */
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    /**
     * Combinator of filter. The method adds a filter to this 
     * object. Note that it is not possible to remove filters.
     * passed. The filters are combined via the addFilter method
     * 
     * @param f  
     */
    public void addFilter(Filter f) {
        filters.add(f);
    }

    /**
     * This filter is satisfied when all filters added satisfies
     * 
     * @param   
     */
    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        } 
        return true;
    }

}
