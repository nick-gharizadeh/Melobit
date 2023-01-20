package com.example.melobit.ui.downloadfragment

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.FragmentDownloadDialogBinding
import com.example.melobit.service.download.manager.DownloadManager
import java.io.File


class DownloadDialogFragment(val song: Song) : DialogFragment() {
    private lateinit var binding: FragmentDownloadDialogBinding
    var REQUEST_CODE_WRITE_STORAGE_PERMISION = 105
    var urlOfTheFile = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button128.setOnClickListener {
            urlOfTheFile = song.audio?.medium?.url.toString()
            checkStoragePermissions(requireActivity())

        }
        binding.button320.setOnClickListener {
            urlOfTheFile = song.audio?.high?.url.toString()
            checkStoragePermissions(requireActivity())

        }
    }

    fun checkStoragePermissions(activity: Activity) {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_STORAGE_PERMISION
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_STORAGE_PERMISION
                    )
                }
            }
        } else {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                if (ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    ) {
                    } else {
                        ActivityCompat.requestPermissions(
                            activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_CODE_WRITE_STORAGE_PERMISION
                        )
                    }
                }
            } else {
                val folder =
                    File(Environment.getExternalStorageDirectory().toString() + "/" + "Download")
                if (!folder.exists()) {
                    folder.mkdirs()
                }
                val fileName = song.title + "-" + (song.artists?.get(0)?.fullName ?: "") + ".mp3"
                DownloadManager.initDownload(
                    requireContext(),
                    urlOfTheFile,
                    folder.absolutePath,
                    fileName
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_WRITE_STORAGE_PERMISION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val folder = File(
                        Environment.getExternalStorageDirectory().toString() + "/" + "Download"
                    )
                    if (!folder.exists()) {
                        folder.mkdirs()
                    }
                    val fileName =
                        song.title + "-" + (song.artists?.get(0)?.fullName ?: "") + ".mp3"
                    DownloadManager.initDownload(
                        requireContext(),
                        urlOfTheFile,
                        folder.absolutePath,
                        fileName
                    )
                }
            }

        }
    }


}