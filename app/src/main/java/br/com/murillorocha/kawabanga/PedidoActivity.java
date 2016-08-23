package br.com.murillorocha.kawabanga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.murillorocha.kawabanga.models.Usuario;

public class PedidoActivity extends AppCompatActivity {

    private TextView tvUsuario;
    private CheckBox cbAtum;
    private CheckBox cbBacon;
    private CheckBox cbMussarela;
    private CheckBox cbCalabresa;

    private RadioGroup rgTamanhoPizza;
    private Spinner spPagamentos;
    private CheckBox cbBordaRecheada;
    private CheckBox cbRecheioExtra;
    private CheckBox cbRefrigerante;
    private CheckBox cbSobremesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        tvUsuario = (TextView) findViewById(R.id.tvUsuario);

        cbAtum = (CheckBox) findViewById(R.id.cbAtum);
        cbBacon = (CheckBox) findViewById(R.id.cbBacon);
        cbMussarela = (CheckBox) findViewById(R.id.cbMussarela);
        cbCalabresa = (CheckBox) findViewById(R.id.cbCalabresa);

        rgTamanhoPizza = (RadioGroup) findViewById(R.id.rgTamanhoPizza);
        spPagamentos = (Spinner) findViewById(R.id.spPagamentos);
        cbBordaRecheada = (CheckBox) findViewById(R.id.cbBordaRecheada);
        cbRecheioExtra = (CheckBox) findViewById(R.id.cbRecheioExtra);
        cbRefrigerante = (CheckBox) findViewById(R.id.cbRefrigerante);
        cbSobremesa = (CheckBox) findViewById(R.id.cbSobremesa);

        //tvUsuario.setText(getIntent().getStringExtra("usuario"));
        Usuario usuario = getIntent().getExtras().getParcelable("usuario");
        tvUsuario.setText(usuario.getLogin());
    }

    public void calcular(View view) {
        int idSelecionado = rgTamanhoPizza.getCheckedRadioButtonId();
        double valorPizza = 0.0;

        switch (idSelecionado) {
            case R.id.rbGrande:
                valorPizza = 50;
                break;
            case R.id.rbMedia:
                valorPizza = 40;
                break;
            case R.id.rbPequena:
                valorPizza = 30;
                break;
        }

        if (cbBordaRecheada.isChecked()) {
            valorPizza += 5.0;
        }

        if (cbRecheioExtra.isChecked()) {
            valorPizza += 8.0;
        }

        if (cbRefrigerante.isChecked()) {
            valorPizza += 11.0;
        }

        if (cbRefrigerante.isChecked()) {
            valorPizza += 20.0;
        }

        String pagamento = (String) spPagamentos.getSelectedItem();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmação do Pedido");
        alert.setMessage("Valor $" + valorPizza + "\n"
                + "Pagamento: " + pagamento);

        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PedidoActivity.this, "Pedido confirmado", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Não", null);
        alert.show();
    }
}
