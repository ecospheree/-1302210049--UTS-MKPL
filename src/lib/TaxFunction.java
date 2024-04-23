package lib;
import java.util.logging.Logger;

public class TaxFunction {
	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	private static final Logger LOGGER = Logger.getLogger(TaxFunction.class.getName());
    private static final int MAX_CHILDREN_FOR_TAX_BENEFIT = 3;
    private static final int TAX_FREE_INCOME_SINGLE = 54000000;
    private static final int TAX_FREE_INCOME_MARRIED = 54000000 + 4500000;
    private static final int TAX_FREE_INCOME_PER_CHILD = 1500000;
    private static final double TAX_RATE = 0.05;

	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            LOGGER.warning("More than 12 months worked per year.");
        }

        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN_FOR_TAX_BENEFIT);

        int taxFreeIncome = isMarried ? TAX_FREE_INCOME_MARRIED : TAX_FREE_INCOME_SINGLE;
        taxFreeIncome += numberOfChildren * TAX_FREE_INCOME_PER_CHILD;

        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - taxFreeIncome;

        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0); 
    }
}
