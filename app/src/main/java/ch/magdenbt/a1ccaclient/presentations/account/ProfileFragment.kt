package ch.magdenbt.a1ccaclient.presentations.account

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ch.magdenbt.a1ccaclient.InitApp
import ch.magdenbt.a1ccaclient.R
import ch.magdenbt.a1ccaclient.databinding.FragmentProfileBinding
import ch.magdenbt.a1ccaclient.model.EmptyFieldException
import ch.magdenbt.a1ccaclient.model.Field
import ch.magdenbt.a1ccaclient.model.accounts.entities.Account
import ch.magdenbt.a1ccaclient.model.settings.AppSettings
import ch.magdenbt.a1ccaclient.utils.setTextIfDifferent
import ch.magdenbt.a1ccaclient.utils.viewModelCreator
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var appSettings: AppSettings
    private val viewModel by viewModelCreator { ProfileViewModel(appSettings) }

    private val activateSaveButton = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (!binding.saveOrSignInButton.isEnabled) {
                binding.saveOrSignInButton.isEnabled = true
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as InitApp).appComponent.profileComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel.currentAccount.observe(viewLifecycleOwner) {
            updateCredentialFields(it)
        }

        viewModel.signingInProgress.observe(viewLifecycleOwner) {
            setProgressBarVisibility()
        }

        viewModel.accountDeletingInProgress.observe(viewLifecycleOwner) {
            setProgressBarVisibility()
        }

        viewModel.isSigned.observe(viewLifecycleOwner) {
            setSaveOrSignInButton(it)
            binding.logoutButton.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.saveOrSignInButton.setOnClickListener {
            tryToSignIn()
            binding.saveOrSignInButton.isEnabled = viewModel.isSigned.value == false
        }

        setCredentialFieldsListener()

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }

        return binding.root
    }

    private fun updateCredentialFields(account: Account?) {
        if (account == null) {
            binding.caURLEditText.text?.clear()
            binding.passwordEditText.text?.clear()
            binding.usernameEditText.text?.clear()
        } else {
            binding.caURLEditText.setTextIfDifferent(account.caURL)
            binding.passwordEditText.setTextIfDifferent(account.password)
            binding.usernameEditText.setTextIfDifferent(account.username)
        }
    }

    private fun setProgressBarVisibility() {
        binding.progressBar.visibility =
            if (viewModel.signingInProgress.value == true || viewModel.accountDeletingInProgress.value == true) View.VISIBLE else View.INVISIBLE
    }

    private fun setSaveOrSignInButton(isSigned: Boolean) {

        if (isSigned) {
            binding.saveOrSignInButton.text = getString(R.string.action_save)
            binding.saveOrSignInButton.isEnabled = false
        } else {
            binding.saveOrSignInButton.text = getString(R.string.action_sign_in)
            binding.saveOrSignInButton.isEnabled = true
        }
    }


    private fun tryToSignIn() {
        resetErrorsOnCredentialFields()

        val newAccount = createAccountFromCredentialFields()
        try {
            newAccount.validate()
            viewModel.updateAccount(newAccount)
        } catch (e: EmptyFieldException) {

            showErrorOnAppropriateField(e)
        }
    }

    private fun resetErrorsOnCredentialFields() {
        binding.caURLInput.isErrorEnabled = false
        binding.passwordTextInput.isErrorEnabled = false
        binding.usernameTextInput.isErrorEnabled = false
    }

    private fun createAccountFromCredentialFields(): Account {
        val newAccount = Account(
            caURL = binding.caURLEditText.text?.toString() ?: "",
            username = binding.usernameEditText.text?.toString() ?: "",
            password = binding.passwordEditText.text?.toString() ?: "",
        )
        return newAccount
    }

    private fun showErrorOnAppropriateField(e: EmptyFieldException) {
        val problemField = when (e.field) {
            Field.CA_URL -> binding.caURLInput
            Field.Password -> binding.passwordTextInput
            Field.Username -> binding.usernameTextInput
        }

        problemField.run {
            requestFocus()
            error = getString(R.string.field_cannot_be_empty)

        }
    }

    private fun setCredentialFieldsListener() {
        binding.usernameEditText.addTextChangedListener(activateSaveButton)
        binding.caURLEditText.addTextChangedListener(activateSaveButton)
        binding.passwordEditText.addTextChangedListener(activateSaveButton)
    }

}